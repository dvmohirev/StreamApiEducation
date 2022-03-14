package Task;

public class Message {
    private Long bankId;
    private String reference;
    private String contractNumber;
    private Long coverLetterId;

    public Message() {
    }

    public Message(Long bankId, String reference, String contractNumber, Long coverLetterId) {
        this.bankId = bankId;
        this.reference = reference;
        this.contractNumber = contractNumber;
        this.coverLetterId = coverLetterId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getCoverLetterId() {
        return coverLetterId;
    }

    public void setCoverLetterId(Long coverLetterId) {
        this.coverLetterId = coverLetterId;
    }
}
