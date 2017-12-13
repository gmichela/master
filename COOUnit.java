package up.coo.tp8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.reflect.*;

public class COOUnit {
    private boolean setUp;
    private boolean tearDown;
    private Method SetUp;
    private Method TearDown;
    private Object obj;
    private List<Method> methodesListe;

    public COOUnit(Object obj) {
        this.setUp = false;
        this.tearDown = false;
        this.obj = obj;
        methodesListe = new ArrayList<Method>();
    }

    public void TestMethods() {
        Method[] tab = this.obj.getClass().getMethods();
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].getName().startsWith("test")) {
                methodesListe.add(tab[i]);
            } 
            else if (tab[i].getName().startsWith("setUp")) {
                this.setUp = true;
                this.SetUp = tab[i];
            } 
            else if (tab[i].getName().startsWith("tearDown")) {
                this.tearDown = true;
                this.TearDown = tab[i];
            }
        }

        /*trier les methodes de test selon leur numero d'ordre*/
        Collections.sort(methodesListe, new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
    }

    public void display(){
    	System.out.println("Les méthodes suivantes sont des méthodes des test : ");
    	
    	for (Method meth: methodesListe) {
    		System.out.println("	-> " + meth.getName());
    	}
    	 System.out.println("-------------------------------------");
         System.out.println("Method setUp : "+setUp);
         System.out.println("Method tearDown : "+tearDown);
    }
    
    public void drive() {
    	   	
		for (Method meth: methodesListe) 
		{
			if(this.setUp) {
				try {
					this.SetUp.invoke(obj);
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
			try {
				meth.invoke(obj);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			if(this.tearDown) {
				try {
					this.TearDown.invoke(obj);
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
		}
    	
    }

}