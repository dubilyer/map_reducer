package map_reduce;


import map_reduce.input.Text;

import static map_reduce.engine.MapReduce.MAP_REDUCE_AND_DISPLAY;

public class Controller {
    public static void main(String[] args) {
        Text
                .GET_TEXT
                .andThen(MAP_REDUCE_AND_DISPLAY);
    }
}
