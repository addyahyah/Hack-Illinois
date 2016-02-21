package azurecomm.data;

/**
 * Created by persinme on 2/20/2016.
 */
public class PostReplies {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("ReplyID")
    private String mReplyId;

    @com.google.gson.annotations.SerializedName("PostID")
    private String mPostId;

    public PostReplies() {}

    public PostReplies(String id, String replyId, String postId) {
        this.setId(id);
        this.setReplyId(replyId);
        this.setPostID(postId);
    }

    public void setId(String id) { this.mId = id; }
    public String getId() { return this.mId; }

    public void setPostID(String postId) { this.mPostId = postId; }
    public String getPostId() { return this.mPostId; }

    public void setReplyId(String replyId) { this.mReplyId = replyId; }
    public String getReplyId() { return this.mReplyId; }
}
