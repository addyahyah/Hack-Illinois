package backEndApi.framework;

import java.util.Map;

/**
 * Created by yangr on 2/20/2016.
 */
public interface IUser {
    public IPost createPost(Map<String, Object> postMap);
}
