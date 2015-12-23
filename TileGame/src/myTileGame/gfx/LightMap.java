package myTileGame.gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import myTileGame.Handler;
import myTileGame.entites.objects.creatures.Player;

public class LightMap {
	private BufferedImage lightMap;
	private List<Light>lights;
	Handler handler;
	public LightMap(Handler handler,Player player) {
		this.handler = handler;
		lightMap = new BufferedImage(handler.getGameWidth(),handler.getGameHeight(),BufferedImage.TYPE_INT_ARGB);
		lights = new ArrayList<Light>();
		Light light = new Light(0,0,200,.9F);
		light.focusOnEntity(player);
		lights.add(light);
		addLight(500, 500, 300, .7F);
	}
	public void addLight( float x,float y,float radius,float lightPower ){
		Light light = new Light(x,y,radius,lightPower);
		lights.add(light);
	}
	
	public void tick(){
		for(Light l : lights){
			l.tick();
		}
	}
	private void invertAlpha(){
		int pixels[] = ((DataBufferInt)lightMap.getRaster().getDataBuffer()).getData();
		for(int i=0;i<pixels.length;i++){
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
			l.render(g2, xOffset, yOffset);
		}
		invertAlpha();
		g2.dispose();
		lightMap.flush();
		
		g.drawImage(lightMap,0,0,null);
	}
}
