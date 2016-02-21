package backEndApi.Api.AzureApi;

/**
 * Created by persinme on 2/20/2016.
 */
public class UserReplies {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("ReplyID")
    private String mReplyId;

    @com.google.gson.annotations.SerializedName("FacebookID")
    private String mUserId;

    public UserReplies() {}

    public UserReplies(String id, String replyId, String postId) {
        this.setId(id);
        this.setReplyId(replyId);
        this.setUserId(postId);
    }

    public void setId(String id) { this.mId = id; }
    public String getId() { return this.mId; }

    public void setUserId(String userId) { this.mUserId = userId; }
    public String getUserId() { return this.mUserId; }

    public void setReplyId(String replyId) { this.mReplyId = replyId; }
    public String getReplyId() { return this.mReplyId; }
}
