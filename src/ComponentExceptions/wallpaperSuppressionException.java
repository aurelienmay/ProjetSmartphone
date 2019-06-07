package ComponentExceptions;

public class wallpaperSuppressionException extends Throwable {

    public wallpaperSuppressionException(){
        System.out.println("Vous ne pouvez pas supprimer l'image utilisée en fond d'écran.");
    }
}
