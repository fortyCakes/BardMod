package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Resonance extends CustomCard {
    public static final String ID = "Resonance";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int ATTACK_DMG = 0;
    private static final int BLOCK = 0;

    private static final int UPGRADE_PLUS_DAMAGE = 2;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public Resonance() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.ALL);

        this.baseDamage = ATTACK_DMG;
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            int debuffsCount = this.damage;
            for (AbstractPower pow : mo.powers)
            {
                if (pow.type == AbstractPower.PowerType.DEBUFF)
                {
                    debuffsCount += Math.max(1, Math.abs(pow.amount));
                }
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, debuffsCount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }

        int buffsCount = this.block;
        for (AbstractPower pow : p.powers)
        {
            if (pow.type == AbstractPower.PowerType.BUFF)
            {
                buffsCount += Math.max(1, Math.abs(pow.amount));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, buffsCount));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
