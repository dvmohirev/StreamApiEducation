package Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AccountRequestProcessorServiceToImpl {
    private final CoverLetterService coverLetterService;
    private final SendRequestListService sendRequestListService;

    public AccountRequestProcessorServiceToImpl(CoverLetterService coverLetterService, SendRequestListService sendRequestListService) {
        this.coverLetterService = coverLetterService;
        this.sendRequestListService = sendRequestListService;
    }


    public void setSendRequests(Map<Long, List<RequestDataRow>> managerRequestDataRows){
        //Объединяем все списки в один
        /*List<RequestDataRow> allDataRows = new ArrayList<>();
        for (List<RequestDataRow> requestList : managerRequestDataRows.values()) {
            for (RequestDataRow row: requestList) {
                //Фильтруем по дате последнего запроса
                if(row.getLastRequestDate().plusDays(30).isBefore(LocalDate.now())){
                    allDataRows.add(row);
                }
            }
        }*/
        Set<Message> messages = managerRequestDataRows.values().stream()
                .flatMap(Collection::stream) // работает также как .flatMap(list -> list.stream())
                .filter(row -> row.getLastRequestDate().plusDays(30).isBefore(LocalDate.now()))
                .collect(Collectors.groupingBy(row -> row.getBankId()))
                .entrySet().stream()
                .map(entry -> coverLetterService.createCoverLetter(
                            entry.getKey(),
                            entry.getValue().stream()
                                    .map(row -> new ContractDto(row.getContractId(), row.getContractNumber()))
                                    .collect(Collectors.toSet())
                    )
                )
                .collect(Collectors.toList()).stream()
                .map(coverLetter -> {
                    return coverLetter.getContractDtoSet().stream()
                            .map(contractDto -> new Message(
                                    coverLetter.getBankId(),
                                    sendRequestListService.generateReference(),
                                    contractDto.getContractNumber(),
                                    coverLetter.getId())
                            )
                            .collect(Collectors.toList());
                    /*new Message(
                            coverLetter.getBankId(),
                            sendRequestListService.generateReference(),

                    )*/
                })
                .flatMap(list -> list.stream())
                .collect(Collectors.toSet());

        /*//Группируем по банку
        Map<Long, List<RequestDataRow>> bankListMap = new HashMap<>();
        //Два варианта, как можно написать
        //Первый вариант
        for (RequestDataRow dataRow: allDataRows) {
            if (!bankListMap.containsKey(dataRow.getBankId())){
                bankListMap.put(dataRow.getBankId(), new ArrayList<>());
            }
            bankListMap.get(dataRow.getBankId()).add(dataRow);
        }
        //Вариант два
        for (RequestDataRow dataRow: allDataRows) {
            bankListMap.computeIfAbsent(dataRow.getBankId(), o -> new ArrayList<>()).add(dataRow);
        }*/

        //Создаем сопроводительные письма
        /*List<CoverLetter> coverLetters = new ArrayList<>();
        for (Map.Entry<Long, List<RequestDataRow>> entry: bankListMap.entrySet()) {
            HashSet<ContractDto> contractDtoSet = new HashSet<>();
            for (RequestDataRow row: entry.getValue()){
                contractDtoSet.add(new ContractDto(row.getContractId(), row.getContractNumber()));
            }
            coverLetters.add(coverLetterService.createCoverLetter(entry.getKey(), contractDtoSet));
        }*/

        //Создаем сообщения
        /*List<Message> messageList = new ArrayList<>();
        for (CoverLetter coverLetter: coverLetters){
            for (ContractDto contractDto: coverLetter.getContractDtoSet()){
                messageList.add(new Message(
                        coverLetter.getBankId(),
                        sendRequestListService.generateReference(),
                        contractDto.getContractNumber(),
                        coverLetter.getId;
                ));
            }
        }*/

        //Отправляем сообщения
        sendRequestListService.sendAll(messages);
    }
}
