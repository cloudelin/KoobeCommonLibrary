package com.koobe.pdf2epub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arthur
 */
public class PageTextObject extends JsonObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x0;
    private int x1;
    private int y0;
    private int y1;
    private boolean eol;
    private String text;	

    @Override
    public Map toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
	map.put("x0", x0);
	map.put("x1", x1);
	map.put("y0", y0);
	map.put("y1", y1);
	map.put("eol", eol);
	map.put("text", text);
	return map;
    }

    @Override
    public void fromMap(Map map) {
	x0 = (Integer) map.get("x0");
	x1 = (Integer) map.get("x1");
	y0 = (Integer) map.get("y0");
	y1 = (Integer) map.get("y1");
	eol = (Boolean) map.get("eol");
	text = (String) map.get("text");
    }
	
    public void setTextSpan(int x0, int x1, int y0, int y1, boolean eol, String text) {
	this.x0 = x0;
	this.x1 = x1;
	this.y0 = y0;
	this.y1 = y1;
	this.text = text;
    }
}
