package bardmod.bard;

import bardmod.bard.powers.HarmonyPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CopyHelper {
    public static void CopyAndPlayCard(AbstractCard card) {
        CopyAndPlayCard(card, 1, false);
    }
    public static void CopyAndPlayCard(AbstractCard card, int cardIndex, boolean isChord) {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        AbstractCard tmp = card.makeSameInstanceOf();

        if (isChord)
        {
            int harmonyAmount = 0;
            HarmonyPower harmony = (HarmonyPower) AbstractDungeon.player.getPower(HarmonyPower.POWER_ID);
            if (harmony != null)
            {
                harmonyAmount = harmony.amount;
            }

            tmp.baseDamage += harmonyAmount;
            tmp.baseBlock += harmonyAmount;
        }

        tmp.tags.add(BardCardTags.IS_CHORD);

        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y + 200.0F * Settings.scale * cardIndex;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F;
        if (m != null) {
            tmp.calculateCardDamage(m);
        }
        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
    }
}
