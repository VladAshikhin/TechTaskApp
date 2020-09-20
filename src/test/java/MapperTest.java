import com.app.objects.Player;
import com.app.objects.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperTest {

    public static void main(String[] args) {

        Task task = taskMapper();

        System.out.println(task.getCompany());
        System.out.println(task.getButtonText());

//        Player player = playerMapper();
//        System.out.println(player.getName() + " " + player.getProfession());

    }

    public static Task taskMapper() {
        String json = "{" +
                "\"company\":\"Ulmart\", " +
                "\"maket\":\"200x400px\", " +
                "\"info\":\"Information\", " +
                "\"platform\":\"New Platform\", " +
                "\"buttonText\":\"Submit\", " +
                "\"slogan\":\"Never give up!\", " +
                "\"description\":\"It's all for fun\", " +
                "\"contacts\":\"911\"" +
                "}";

        //String company = "{\"name\":\"comp\"}";

        //System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();

        Task task = new Task();

        try {
          /*  Map<String, String> data = mapper.readValue(json, Map.class);
            System.out.println(data);
            System.out.println(data.get("platform"));*/

            task = mapper.readValue(json, Task.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    public static Player playerMapper() {
        String json = "{\"name\":\"Patrick\", \"profession\":\"Plumber\"}";
        ObjectMapper mapper = new ObjectMapper();
        Player player = new Player();

        try{
            player = mapper.readValue(json, Player.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }

}
