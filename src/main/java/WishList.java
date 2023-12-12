import java.util.ArrayList;
import java.util.List;

public class WishList {
    private String listName;
    private String creationDate;
    private List<Wish> wishes;

    public WishList(){
    }

    public WishList(String listName, String creationDate) {
        this.listName=listName;
        this.creationDate=creationDate;
        this.wishes = new ArrayList<>();
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public void removeWish(Wish wish) {
        wishes.remove(wish);
    }

    public List<Wish> getWishes() {
        return new ArrayList<>(wishes);
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "WishList{" +
                ", listName='" + listName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", wishes=" + wishes +
                '}';
    }
}

