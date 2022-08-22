package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.actions.unique.PlayCardFromDeckAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MiddleC extends CustomCard {
    public static final String ID = "MiddleC";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    private static final int BLOCK = 6;
    private static final int DRAW_CARDS = 1;
    private static final int UPGRADE_PLUS_DRAW_CARDS = 1;

    public MiddleC() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.NONE);
        tags.add(BardCardTags.NOTE_C);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
        int deckSize = p.drawPile.size();
        AbstractCard CardA = null; AbstractCard CardB = null;

        for(int i = 0; i < deckSize; i++)
        {
            AbstractCard card = p.drawPile.getNCardFromTop(i);
            if (card.hasTag(BardCardTags.NOTE_A)){
                CardA = card;
            }
            if (card.hasTag(BardCardTags.NOTE_B)){
                CardB = card;
            }
        }

        if (CardA == null && CardB != null)
        {
            PlayCard(CardB, m);
        }
        if (CardB == null && CardA != null)
        {
            PlayCard(CardA, m);
        }
        if (CardA != null && CardB != null)
        {
            if (upgraded) {
                PlayCard(CardA, m);
                PlayCard(CardB, m);
            }
            else if (AbstractDungeon.cardRandomRng.randomBoolean()) {
                PlayCard(CardA, m);
            }
            else {
                PlayCard(CardB, m);
            }

        }

    }

    private static void PlayCard(AbstractCard card, AbstractMonster m) {
        // TODO make this work properly - e.g. PlayTopCardAction.
        AbstractDungeon.actionManager.addToBottom(new PlayCardFromDeckAction(m, card));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
