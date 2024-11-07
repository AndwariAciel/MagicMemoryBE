package de.andwari.memory.backend.model.scryfall;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SetsRootModel {

    private String object;
    private boolean has_more;
    private ArrayList<SetModel> data;
}
