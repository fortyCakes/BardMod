package bardmod.bard;

import bardmod.bard.powers.ScalePower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ScaleHelper {
    protected static final Color BLUE_BORDER_GLOW_COLOR = new Color(0.2F, 0.9F, 1.0F, 0.25F);
    protected static final Color GOLD_BORDER_GLOW_COLOR = Color.GOLD.cpy();

    public static int LastCost = -999;
    public static int ScaleAmount = 1;

    public static void receiveOnBattleStart() {
        System.out.println("Resetting Scale to 1 at start of battle.");
        ScaleAmount = 1;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ScalePower(1), 1));
    }

    public static void receiveCardUsed(AbstractCard card) {
        ScaleAmount = ScaleAmount();

        int cost = card.cost == -1 ? card.energyOnUse : card.costForTurn;
        if (WasScale(cost) || !AbstractDungeon.player.hasPower(ScalePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ScalePower(1), 1));
        } else {
            ScalePower scalePower = (ScalePower) AbstractDungeon.player.getPower(ScalePower.POWER_ID);
            scalePower.amount = 1;
        }

        if (WasScale(cost))
        {
            TriggerOnScaleEvents();
        }

        if (card.costForTurn == -1) {
            LastCost = card.energyOnUse;
        }
        else {
            LastCost = card.costForTurn;
        }
    }

    private static void TriggerOnScaleEvents() {

        TriggerEventsForPlayerPowers();
        TriggerEventsForPlayerRelics();
    }

    private static void TriggerEventsForPlayerRelics() {
        for (AbstractRelic rel : AbstractDungeon.player.relics){
            if (rel instanceof  ITriggerOnScale){
                ((ITriggerOnScale)rel).onScale();
            }
        }
    }

    private static void TriggerEventsForPlayerPowers() {
        for (AbstractPower pow : AbstractDungeon.player.powers){
            if (pow instanceof ITriggerOnScale){
                ((ITriggerOnScale)pow).onScale();
            }
        }
    }

    private static int ScaleAmount() {
        if (AbstractDungeon.player.hasPower(ScalePower.POWER_ID))
        {
            return AbstractDungeon.player.getPower(ScalePower.POWER_ID).amount;
        } else {
            return 1;
        }
    }

    public static boolean WillScale(int cost){
        return (cost == LastCost + 1 || cost == LastCost - 1);
    }

    public static boolean WasScale(int cost){
        return (cost == LastCost + 1 || cost == LastCost - 1);
    }

    public static void GlowCheck(AbstractCard card) {
        if (ScaleHelper.WillScale(card.cost == -1 ? card.energyOnUse : card.costForTurn)){
            card.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            card.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public static void applyToBlock(AbstractCard card) {
        card.block += ScaleHelper.ScaleAmount;
        card.isBlockModified = true;
    }

    public static void applyToDamage(AbstractCard card) {
        card.damage += ScaleHelper.ScaleAmount;
        card.isDamageModified = true;
    }
}
