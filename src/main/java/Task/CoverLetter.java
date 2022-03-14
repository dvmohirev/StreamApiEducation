package Task;

import java.util.List;

public class CoverLetter {

    private Long bankId;
    private Long id;

    public CoverLetter(Long bankId, Long id) {
        this.bankId = bankId;
        this.id = id;
    }

    public CoverLetter() {
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List getContractDtoSet(List<Message> list){
        return null;
    }
}
