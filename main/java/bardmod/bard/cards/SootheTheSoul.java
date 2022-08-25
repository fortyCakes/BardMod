package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.powers.HarmonyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class SootheTheSoul extends CustomCard {
    public static final String ID = "SootheTheSoul";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int HARMONY = 1;
    private static final int UPGRADE_PLUS_HARMONY = 1;

    public SootheTheSoul() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.SELF);
        tags.add(BardCardTags.NOTE_C);
        this.baseMagicNumber = HARMONY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HarmonyPower(p, magicNumber), magicNumber));

        ArrayList<AbstractPower> debuffs = new ArrayList<>();
        for(AbstractPower pow : p.powers)
        {
            if (pow.type == AbstractPower.PowerType.DEBUFF)
            {
                debuffs.add(pow);
            }
        }

        if (upgraded)
        {
            for (AbstractPower debuff : debuffs) {
                addToBot(new RemoveSpecificPowerAction(p, p, debuff));
            }
        } else {
            int index = AbstractDungeon.cardRandomRng.random(debuffs.size()) - 1;
            addToBot(new RemoveSpecificPowerAction(p, p, debuffs.get(index)));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HARMONY);
        }
    }
}
