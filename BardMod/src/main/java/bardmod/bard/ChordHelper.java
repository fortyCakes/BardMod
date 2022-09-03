package bardmod.bard;

import bardmod.bard.powers.ArpeggioPower;
import bardmod.bard.powers.ChordPower;
import bardmod.bard.powers.DaCapoPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChordHelper {

    public static void receiveCardUsed(AbstractCard card) {

        if (card.hasTag(BardCardTags.IS_CHORD)) return;

        boolean cardUsedAsNote = false;

        boolean hasArpeggio = AbstractDungeon.player.hasPower(ArpeggioPower.POWER_ID);

        //noinspection ConstantConditions
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_A)){
            cardUsedAsNote = tryAddNoteA(card);
        }
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_B)){
            cardUsedAsNote = tryAddNoteB(card);
        }
        if (!cardUsedAsNote && card.hasTag(BardCardTags.NOTE_C)){
            cardUsedAsNote = tryAddNoteC(card);
        }

        if (hasArpeggio)
        {
            if (!cardUsedAsNote && card.type == AbstractCard.CardType.ATTACK)
            {
                cardUsedAsNote = tryAddNoteA(card);
            }
            if (!cardUsedAsNote && card.type == AbstractCard.CardType.SKILL && card.baseBlock > 0)
            {
                cardUsedAsNote = tryAddNoteB(card);
            }
            if (!cardUsedAsNote && card.type == AbstractCard.CardType.SKILL && card.baseBlock <= 0)
            {
                cardUsedAsNote = tryAddNoteC(card);
            }
        }

        if (cardUsedAsNote) {
            checkIfChordComplete();
        }

    }

    private static void checkIfChordComplete() {
        ChordPower chord = (ChordPower) AbstractDungeon.player.getPower(ChordPower.POWER_ID);

        if (chord.ChordComplete())
        {
            TriggerOnChordEvents();
            PlayChordCards(chord);

            if (AbstractDungeon.player.hasPower(DaCapoPower.POWER_ID))
            {
                int daCapo = AbstractDungeon.player.getPower(DaCapoPower.POWER_ID).amount;
                for (int i = 0; i < daCapo; i++)
                {
                    PlayChordCards(chord);
                }
            }

            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ChordPower.POWER_ID));
        }
    }

    private static void TriggerOnChordEvents() {
        TriggerEventsForPlayerPowers();
    }

    private static void TriggerEventsForPlayerPowers() {
        for (AbstractPower pow : AbstractDungeon.player.powers){
            if (pow instanceof ITriggerOnChord){
                ((ITriggerOnChord)pow).onChord();
            }
        }
    }

    private static void PlayChordCards(ChordPower chord) {
        CopyHelper.CopyAndPlayCard(chord.CardC, 1);
        CopyHelper.CopyAndPlayCard(chord.CardB, 2);
        CopyHelper.CopyAndPlayCard(chord.CardA, 3);
    }

    private static boolean tryAddNoteA(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteA(abstractCard.makeSameInstanceOf());
    }

    private static boolean tryAddNoteB(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteB(abstractCard.makeSameInstanceOf());
    }

    private static boolean tryAddNoteC(AbstractCard abstractCard) {
        ChordPower chord = ensureChordPowerExists();
        return chord.SetNoteC(abstractCard.makeSameInstanceOf());
    }

    private static ChordPower ensureChordPowerExists() {
        if (!AbstractDungeon.player.hasPower(ChordPower.POWER_ID)) {
            AbstractGameAction giveChord = new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ChordPower(AbstractDungeon.player));
            giveChord.update();
        }
        return (ChordPower) AbstractDungeon.player.getPower(ChordPower.POWER_ID);
    }
}
