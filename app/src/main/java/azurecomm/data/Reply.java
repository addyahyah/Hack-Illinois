package azurecomm.data;

import java.sql.Time;
import java.util.Date;

/**
 * Created by persinme on 2/20/2016.
 */
public class Reply {
    /**
     * Reply id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("Message")
    private String mMessage;

    @com.google.gson.annotations.SerializedName("Time")
    private java.sql.Timestamp mTime;

    public Reply() {
    }
    public Reply(String id, String message, java.sql.Timestamp time) {
        this.setReplyId(id);
        this.setMessage(message);
        this.setTime(time);
    }

    public void setReplyId(String id) { this.mId = id; }
    public String getReplyId() { return this.mId; }

    public void setMessage(String message) { this.mMessage = message; }
    public String getMessage() { return this.mMessage; }

    public void setTime(java.sql.Timestamp time) { this.mTime = time; }
    public Date getTime() { return this.mTime; }
}
