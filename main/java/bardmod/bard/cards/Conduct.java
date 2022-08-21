package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.actions.unique.DoubleSadAction;
import bardmod.bard.actions.unique.TripleSadAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Conduct extends CustomCard {
    public static final String ID = "Conduct";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW_CARDS = 2;
    private static final int UPGRADE_PLUS_DRAW_CARDS = 1;

    public Conduct() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.SELF);
        tags.add(BardCardTags.NOTE_C);
        this.baseMagicNumber = DRAW_CARDS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, draw));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW_CARDS);
        }
    }
}
