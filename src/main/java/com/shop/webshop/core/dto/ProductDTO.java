package com.shop.webshop.core.dto;

import com.shop.webshop.core.constant.CoreConstant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductDTO extends AbstractDTO<ProductDTO>{
    private String productCode;
    private String productName;
    private Long price;
    private Integer sale;
    private Long saleOf;
    private String image;
    private String imageUrl;
    private MultipartFile fileImage;
    private String description;
    private String store;
    private Integer status;
    private List<CartProductDTO> cartProducts;
    private List<CategoryDTO> categories;
    public ProductDTO() {
    }

    public String getImageUrl() {
        if (image == null) {
            imageUrl = "/template/image/no-image.png";
        } else {
            imageUrl = "/" + CoreConstant.FOLDER_UPLOAD + "/" + CoreConstant.PRODUCT_IMAGE + "/" + getId() + "/" + image;
        }
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public List<CartProductDTO> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDTO> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MultipartFile getFileImage() {
        return fileImage;
    }

    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }

    public Long getSaleOf() {
        if (sale == null || sale == 0) {
            return price;
        }
        return price * (100 - sale) / 100;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setSaleOf(Long saleOf) {
        this.saleOf = saleOf;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
