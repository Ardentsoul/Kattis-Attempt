import java.util.Scanner;

public class Peasoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputRestaurant = sc.nextLine();
        int inputRestaurantNum = Integer.parseInt(inputRestaurant);
        if (inputRestaurantNum >= 1 && inputRestaurantNum <= 10) {
            for(int i = 0; i < inputRestaurantNum; i++) {
                String menuItems = sc.nextLine();
                int menuItemsNum = Integer.parseInt(menuItems);
                if (menuItemsNum >= 1 && menuItemsNum <= 10) {
                    String restaurantName = sc.nextLine();
                    boolean pea = false;
                    boolean pan = false;
                    for(int j = 0; j < menuItemsNum; j++) {
                        String nameAndItems = sc.nextLine();
                        if(nameAndItems.equals("pea soup")) {
                            pea = true;
                        }
                        if(nameAndItems.equals("pancakes")) {
                            pan =true;
                        }
                    }
                    if(pea && pan) {
                        System.out.println(restaurantName);
                        return;
                    }
                }
            }
            System.out.println("Anywhere is fine I guess");
        }
    }
}

/*
Get number of int input.

 */
