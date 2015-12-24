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
	private float[]lightAtPoint;
	private int width,height;
	public LightMap(Handler handler) {
		this.handler = handler;
		width = handler.getGameWidth();
		height = handler.getGameHeight();
		lightMap = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)lightMap.getRaster().getDataBuffer()).getData();
		lights = new ArrayList<Light>();
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
		lightAtPoint = new float[width*height];
		for(Light l : lights){
			l.tick();
		}
	}
	private void invertAlpha(){
		for(int i=0;i<width*height;i++){
			Color color = new Color(pixels[i],true);
			pixels[i] = new Color(
					color.getRed(),
					color.getBlue(),
					color.getGreen(),
					255-color.getAlpha()
					).getRGB();
		}
	}
	private void distributeLight(float xOffset,float yOffset){
		for(int i=0;i<width*height;i++){
			for(Light l : lights){
				int ty = i/width;
				int tx = i -ty*width; 
				int lx = (int)(l.getX()-xOffset);
				int ly = (int)(l.getY()-yOffset);
				if((ty-ly)*(ty-ly) + (tx-lx)*(tx-lx) <= l.getRadius()*l.getRadius()){
					lightAtPoint[i] += l.getPower()*(l.getRadius()-Math.sqrt((ty-ly)*(ty-ly) + (tx-lx)*(tx-lx)))/l.getRadius();
				}
			}
			lightAtPoint[i] = Math.min(lightAtPoint[i], 1f);
		}
		for(int i=0;i<width*height;i++){
			pixels[i] = new Color(
					0,
					0,
					0,
					1f-lightAtPoint[i]
					).getRGB();
		}
	}
	public void render(Graphics2D g,float xOffset,float yOffset){
//		
//		invertAlpha();
		distributeLight(xOffset,yOffset);
		g.drawImage(lightMap,0,0,null);
	}
}
