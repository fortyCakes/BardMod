package bardmod.bard.potions;

import bardmod.bard.StoryHelper;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class StoryPotion extends CustomPotion {
    public static final String POTION_ID = "StoryPotion";
    public static final PotionRarity RARITY = PotionRarity.COMMON;
    public static final PotionSize SIZE = PotionSize.CARD;
    public static final PotionColor COLOR = PotionColor.ANCIENT;

    public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public StoryPotion() {
        super(NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.CARD, PotionColor.ANCIENT);
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
        for (int i = 0; i < getEffect(); i++) {
            AbstractCard freeStory = StoryHelper.generateStoryCard(true);
            if (freeStory != null) {
                freeStory.setCostForTurn(0);
                addToBot(new MakeTempCardInHandAction(freeStory));
            }
        }
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return 1;
    }

    private int getEffect(){
        return (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) ? 1 : 2;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new StoryPotion();
    }
}

