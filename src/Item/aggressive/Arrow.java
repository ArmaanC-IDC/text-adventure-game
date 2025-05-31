// Arrow.java
package item.aggressive;

import item.Item;
import item.ItemLoader;

public class Arrow extends Item {
    public Arrow() {
        super("Arrow", ItemLoader.getItemConfig("arrow", "weight"));
    }


    public String getType() {
        return "arrow";
    }
}
