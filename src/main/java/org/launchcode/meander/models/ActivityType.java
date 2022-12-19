package org.launchcode.meander.models;

import java.util.ArrayList;
import java.util.HashMap;

public enum ActivityType {

    FOODANDDRINK("food-drink", "All things related to food and drink."),
    MUSEUMS("museums", "For those who want to recreate the museum scene in Ferris Bueller's day off."),
    ARTSCULTURE("arts-culture", "Fall in love with your destination by experiencing its unique culture."),
    NIGHTLIFE("nightlife", "Clubs, bars, parties, and more."),
    HISTORY("history", "For those off-the-beaten-path historical sites."),
    PHOTOGRAPHY("photography", "These Insta-worthy destinations will have your followers begging for more content."),
    ADVENTURE("adventure", "For those who like to live life on the wild side."),
    HIDDENGEM("hidden-gem", "You won't find these destinations on a map, but they just might become your new go-to spot!"),
    OUTDOORS("outdoors", "For those off-the-beaten-path outdoors destinations."),
    MISC("misc", "Everything else!"),
    BARS("bars", "From dive bars to upscale cocktail joints, who doesn't enjoy a drink every now and then?");

    private final String displayName;
    private final String description;

    ActivityType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getActivityHashtag() {
        return "#" + displayName;
    }

    public static HashMap<String, String> showAllActivities(){

        HashMap<String, String> activitiesList = new HashMap<>();

        for(ActivityType activity : ActivityType.values()) {
            activitiesList.put(activity.getDisplayName(), activity.getDescription());
        }

        return activitiesList;
    }
}
