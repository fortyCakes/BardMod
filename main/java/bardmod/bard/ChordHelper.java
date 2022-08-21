package bardmod.bard;

import bardmod.bard.powers.ArpeggioPower;
import bardmod.bard.powers.ChordPower;
import bardmod.bard.powers.DaCapoPower;
import bardmod.bard.powers.HarmonyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChordHelper {

    public static void receiveCardUsed(AbstractCard card) {

        if (card.purgeOnUse) return;

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

    private static void PlayChordCards(ChordPower chord) {
        CopyAndPlayCard(chord.CardC, 1);
        CopyAndPlayCard(chord.CardB, 2);
        CopyAndPlayCard(chord.CardA, 3);
    }

    private static void CopyAndPlayCard(AbstractCard card, int cardIndex) {
        AbstractMonster m = AbstractDungeon.getRandomMonster();

        int harmonyAmount = 0;
        HarmonyPower harmony = (HarmonyPower) AbstractDungeon.player.getPower(HarmonyPower.POWER_ID);
        if (harmony != null)
        {
            harmonyAmount = harmony.amount;
        }

        AbstractCard tmp = card.makeSameInstanceOf();
        tmp.baseDamage += harmonyAmount;
        tmp.baseBlock += harmonyAmount;

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
