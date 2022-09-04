package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.ScaleHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pentatonic extends CustomCard {
    public static final String ID = "Pentatonic";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int ATTACK_DMG = 5;
    private static final int DRAW_CARD = 1;
    private static final int DRAW_CARD_THRESHOLD = 5;
    private static final int UPGRADE_DRAW_CARD= 1;

    public Pentatonic() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = DRAW_CARD;
        this.magicNumber = baseMagicNumber;
    }

    public void triggerOnGlowCheck() {
        if (ScaleHelper.ScaleAmount >= DRAW_CARD_THRESHOLD) {
            glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardDamage(m);
        if (ScaleHelper.ScaleAmount > 0)
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));

        if (ScaleHelper.ScaleAmount >= DRAW_CARD_THRESHOLD)
        {
            addToBot(new DrawCardAction(p, magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DRAW_CARD);
        }
    }
}
