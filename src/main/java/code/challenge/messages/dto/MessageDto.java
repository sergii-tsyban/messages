package code.challenge.messages.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageDto {

    private String user;
    private String message;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date postedDate;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
}
