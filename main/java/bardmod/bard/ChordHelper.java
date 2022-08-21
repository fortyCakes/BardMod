package bardmod.bard;

import bardmod.bard.powers.ChordPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChordHelper {

    public static void receiveCardUsed(AbstractCard card) {

        if (card.purgeOnUse) return;

        boolean cardUsedAsNote = false;
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_A)){
            cardUsedAsNote = tryAddNoteA(card);
        }
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_B)){
            cardUsedAsNote = tryAddNoteB(card);
        }
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_C)){
            cardUsedAsNote = tryAddNoteC(card);
        }

        if (cardUsedAsNote) {
            checkIfChordComplete();
        }

    }

    private static void checkIfChordComplete() {
        ChordPower chord = (ChordPower) AbstractDungeon.player.getPower(ChordPower.POWER_ID);

        if (chord.ChordComplete())
        {
            CopyAndPlayCard(chord.CardA);
            CopyAndPlayCard(chord.CardB);
            CopyAndPlayCard(chord.CardC);
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ChordPower.POWER_ID));
        }
    }

    private static void CopyAndPlayCard(AbstractCard card) {
        AbstractMonster m = null;

        /*if (action.target != null) {
            m = (AbstractMonster)action.target;
        }*/

        m = AbstractDungeon.getRandomMonster();

        AbstractCard tmp = card.makeSameInstanceOf();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F;
        if (m != null) {
            tmp.calculateCardDamage(m);
        }
        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);

        AbstractDungeon.player.discardPile.addToTop(card);
    }

    private static boolean tryAddNoteA(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteA(abstractCard);
    }

    private static boolean tryAddNoteB(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteB(abstractCard);
    }

    private static boolean tryAddNoteC(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteC(abstractCard);
    }

    private static ChordPower ensureChordPowerExists() {
        if (!AbstractDungeon.player.hasPower(ChordPower.POWER_ID)) {
            AbstractGameAction giveChord = new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ChordPower(AbstractDungeon.player));
            giveChord.update();
        }
        ChordPower chord = (ChordPower) AbstractDungeon.player.getPower(ChordPower.POWER_ID);
        return chord;
    }
}
