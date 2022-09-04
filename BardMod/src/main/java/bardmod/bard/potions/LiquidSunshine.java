package bardmod.bard.potions;

import bardmod.bard.powers.HappyPower;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class LiquidSunshine extends CustomPotion {
    public static final String POTION_ID = "LiquidSunshine";
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.BOTTLE;
    public static final PotionColor COLOR = PotionColor.ENERGY;

    public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public LiquidSunshine() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HappyPower(AbstractDungeon.player, getEffect()), getEffect()));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }

    private int getEffect(){
        return (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) ? 4 : 8;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new LiquidSunshine();
    }
}

