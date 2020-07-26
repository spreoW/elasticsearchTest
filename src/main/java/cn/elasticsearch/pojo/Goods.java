package cn.elasticsearch.pojo;

public class Goods {
    private String image;
    private String price;
    private String title;

    @Override
    public String toString() {
        return "Goods{" +
                "image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
