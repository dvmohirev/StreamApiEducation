package Task;

import java.security.PrivateKey;
import java.util.Objects;

public class ContractDto {
    private  Long contractId;
    private String contractNumber;

    public ContractDto() {
    }

    public ContractDto(Long contractId, String contractNumber) {
        this.contractId = contractId;
        this.contractNumber = contractNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractDto that = (ContractDto) o;
        return Objects.equals(contractId, that.contractId) && Objects.equals(contractNumber, that.contractNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractId, contractNumber);
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
