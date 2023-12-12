public class Wish {
    private String dateCreated;
    private String recipient;
    private String wishContent;

    public Wish(){
    }

    public Wish(String dateCreated, String recipient, String wishContent){
        this.dateCreated=dateCreated;
        this.recipient=recipient;
        this.wishContent=wishContent;
    }

    public void addWish(int wishId, String dataCreated, String recipient, String wishContent) {
        Wish w = new Wish(this.dateCreated, this.recipient, this.wishContent);
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    @Override
    public String toString() {
        return "Wish{" +
                ", dateCreated='" + dateCreated + '\'' +
                ", recipient='" + recipient + '\'' +
                ", wishContent='" + wishContent + '\'' +
                '}';
    }

}

