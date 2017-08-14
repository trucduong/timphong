package core.dao.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ContentDto {
    @Min(1)
    private Long id;
    
    @NotBlank
    private String content;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
