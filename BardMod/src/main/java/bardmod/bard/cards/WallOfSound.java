package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.powers.PuppetPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WallOfSound extends CustomCard {
    public static final String ID = "WallOfSound";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int PUPPET = 1;

    private static final int UPGRADE_PLUS_BLOCK = 4;

    public WallOfSound() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.ALL);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = PUPPET;
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, block));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
        {
            ApplyPowerAction applyAction = new ApplyPowerAction(mo, p, new PuppetPower(p, magicNumber), magicNumber);
            applyAction.update();
            PuppetPower pow = (PuppetPower) mo.getPower(PuppetPower.POWER_ID);
            if (pow != null) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, pow.amount));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
