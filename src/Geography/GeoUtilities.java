package Geography;

public class GeoUtilities {

    public static int getDistance(Country a, Country b){
        switch(a){
            case England:
                if(b == Country.England) return 1;
                if(b == Country.Scotland) return 2;
                if(b == Country.Wales) return 2;
                if(b == Country.Northern_Ireland) return 3;
                break;
            case Northern_Ireland:
                if(b == Country.England) return 3;
                if(b == Country.Scotland) return 3;
                if(b == Country.Wales) return 3;
                if(b == Country.Northern_Ireland) return 1;
                break;
            case Wales:
                if(b == Country.England) return 2;
                if(b == Country.Scotland) return 2;
                if(b == Country.Wales) return 1;
                if(b == Country.Northern_Ireland) return 2;
                break;
            case Scotland:
                if(b == Country.England) return 2;
                if(b == Country.Scotland) return 1;
                if(b == Country.Wales) return 2;
                if(b == Country.Northern_Ireland) return 3;
                break;
            default:
                return 100;
        }
        return 100;
    }
}
