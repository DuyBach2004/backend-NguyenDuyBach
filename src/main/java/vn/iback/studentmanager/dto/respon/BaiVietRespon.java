package vn.iback.studentmanager.dto.respon;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaiVietRespon {

    private String title;

    private String image;
    private UserRespon createBy;

    public BaiVietRespon(String title, String image, UserRespon createBy) {
        this.title = title;
        this.image = image;
        this.createBy = createBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserRespon getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserRespon createBy) {
        this.createBy = createBy;
    }
}
