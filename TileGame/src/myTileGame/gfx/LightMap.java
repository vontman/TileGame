package myTileGame.gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import myTileGame.Handler;
import myTileGame.objects.entites.Entity;

public class LightMap {
	private BufferedImage lightMap;
	private List<Light>lights;
	private Handler handler;
	private int[]pixels;
	private int l;
	public LightMap(Handler handler) {
		this.handler = handler;
		lightMap = new BufferedImage(handler.getGameWidth(),handler.getGameHeight(),BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)lightMap.getRaster().getDataBuffer()).getData();
		lights = new ArrayList<Light>();
		l = pixels.length;
	}
	public void addLight( float x,float y,float radius,float lightPower ){
		Light light = new Light(x,y,radius,lightPower);
		lights.add(light);
	}
	public void addLight(Entity e,float radius,float lightPower ){
		Light light = new Light(0,0,radius,lightPower);
		light.focusOnEntity(e);
		lights.add(light);
	}
	public void removeLight(Light light){
		lights.remove(light);
	}
	
	public void tick(){
		for(Light l : lights){
			l.tick();
		}
	}
	private void invertAlpha(){
		for(int i=0;i<l;i++){
			Color color = new Color(pixels[i],true);
			pixels[i] = new Color(
					color.getRed(),
					color.getBlue(),
					color.getGreen(),
					255-color.getAlpha()
					).getRGB();
		}
	}
	public void render(Graphics2D g,float xOffset,float yOffset){
		Graphics2D g2 = (Graphics2D) lightMap.getGraphics();
		g2.setBackground(new Color(0,0,0,0));
		g2.clearRect(0, 0, handler.getGameWidth(), handler.getGameHeight());
		for(Light l : lights){
//			l.render(g2, xOffset, yOffset);
			g2.drawImage(l.getImage(), (int)(l.getX()-xOffset), (int)(l.getY()-yOffset), null);
		}
		invertAlpha();
		g2.dispose();
		lightMap.flush();
		
		g.drawImage(lightMap,0,0,null);
	}
}
