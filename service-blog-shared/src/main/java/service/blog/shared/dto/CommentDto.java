package service.blog.shared.dto;

import java.util.Date;

import core.dao.dto.BaseDto;
import core.dao.dto.EntityStatus;

public class CommentDto extends BaseDto {
    public static final String TARGET = "target";
    
    private long targetId;

    private String questionName;

    private String createdBy;

    private Date createdDate;

    private String content;

    private EntityStatus status;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

}
