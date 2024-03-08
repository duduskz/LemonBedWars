package cn.lemonnetwork.lemonbedwars.Game.Arena.Menu;

public class ShopItem {
    String item;
    int pos,amount,costamount;
    String displayname;
    String cost;
    public ShopItem(String item,String dpname,String cost,int pos,int amount,int costamount){
        this.amount=amount;
        this.pos=pos;
        this.cost=cost;
        this.costamount=costamount;
        this.displayname=dpname;
        this.item=item;
    }
}
