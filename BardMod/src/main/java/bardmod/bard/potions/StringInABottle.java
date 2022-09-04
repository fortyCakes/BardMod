package bardmod.bard.potions;

import bardmod.bard.powers.PuppetPower;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class StringInABottle extends CustomPotion {
    public static final String POTION_ID = "StringInABottle";
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.GHOST;
    public static final PotionColor COLOR = PotionColor.ELIXIR;

    public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public StringInABottle() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        this.isThrown = true;
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
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new PuppetPower(target, getEffect()), getEffect()));
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }

    private int getEffect(){
        return (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) ? 15 : 30;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new StringInABottle();
    }
}

