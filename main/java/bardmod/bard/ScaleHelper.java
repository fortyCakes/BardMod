package bardmod.bard;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScaleHelper {
    protected static final Color BLUE_BORDER_GLOW_COLOR = new Color(0.2F, 0.9F, 1.0F, 0.25F);
    protected static final Color GOLD_BORDER_GLOW_COLOR = Color.GOLD.cpy();

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

    public static void GlowCheck(AbstractCard card) {
        if (ScaleHelper.WillScale(card.cost)){
            card.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            card.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
