package Lesson_6.HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 10.12.16.
 */
class Championship {

    private List<League> allLeagues;

    Championship() {
        allLeagues = new ArrayList<>();
    }

    List<League> getAllLeagues() {
        return allLeagues;
    }
}
