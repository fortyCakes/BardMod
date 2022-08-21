package bardmod.bard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScaleHelper {
    public static boolean WillScale(int cost){
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) return false;

        AbstractCard lastCard = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
        return (lastCard.cost == cost + 1 || lastCard.cost == cost - 1);
    }

    public static boolean WasScale(int cost){
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() || AbstractDungeon.actionManager.cardsPlayedThisCombat.size() == 1) return false;

        AbstractCard lastCard = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
        return (lastCard.cost == cost + 1 || lastCard.cost == cost - 1);
    }
}
