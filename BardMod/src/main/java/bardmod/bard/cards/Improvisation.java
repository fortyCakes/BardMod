package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.powers.PuppetPower;
import bardmod.bard.powers.SadnessPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;

import java.util.ArrayList;
import java.util.Arrays;

public class Improvisation extends CustomCard {
    public static final String ID = "Improvisation";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 10;
    private static final int NUM_DEBUFFS = 1;

    private static final int DEBUFF_STRENGTH = 3;
    private static final int UPGRADE_PLUS_NUM = 1;

    public Improvisation() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = NUM_DEBUFFS;
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.POISON));

        for (int i = 0; i < magicNumber; i++)
        {
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
            {
                applyRandomDebuffToMonster(mo);
            }
        }
    }

    private void applyRandomDebuffToMonster(AbstractMonster mo) {
        ArrayList<Class> debuffs = new ArrayList<>(
                Arrays.asList(
                        SadnessPower.class,
                        WeakPower.class,
                        VulnerablePower.class,
                        DexterityPower.class,
                        StrengthPower.class,
                        PuppetPower.class,
                        PoisonPower.class,
                        BlockReturnPower.class,
                        ChokePower.class,
                        ConstrictedPower.class)
        );

        int debuffIndex = AbstractDungeon.cardRandomRng.random(debuffs.size());
        int amount = (debuffIndex == 3 || debuffIndex == 4) ? -DEBUFF_STRENGTH : +DEBUFF_STRENGTH;

        AbstractPower instance = getInstanceOfPower(AbstractDungeon.player, mo, debuffIndex, amount);

        if (instance != null)
        {
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, instance, amount));
        }

    }

    private AbstractPower getInstanceOfPower(AbstractPlayer p, AbstractMonster m, int debuffIndex, int debuffStrength) {
        switch(debuffIndex)
        {
            case 0: return new SadnessPower(m, debuffStrength);
            case 1: return new WeakPower(m, debuffStrength, false);
            case 2: return new VulnerablePower(m, debuffStrength, false);
            case 3: return new DexterityPower(m, debuffStrength);
            case 4: return new StrengthPower(m, debuffStrength);
            case 5: return new PuppetPower(m, debuffStrength);
            case 6: return new PoisonPower(m, p, debuffStrength);
            case 7: return new BlockReturnPower(m, debuffStrength);
            case 8: return new ChokePower(m, debuffStrength);
            case 9: return new ConstrictedPower(m, p, debuffStrength);
            default: return null;
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_NUM);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
