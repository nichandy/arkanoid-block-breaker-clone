package org.nhandy;

import java.awt.*;

public interface Drawable {

    public void Draw(Graphics g);

    public void setDrawable(boolean canDraw);

    public boolean isDrawable();

}
