package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.cards.OctetResolution;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class OctetPower
        extends AbstractPower {
    public static final String POWER_ID = "OctetPower";
    public static final String NAME = "Octet Form";
    public static final String[] DESCRIPTIONS =  new String[]{
            "When you play a card, copy it ", " times, then end your turn."
    };


    public OctetPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("POWER_STRENGTH", 0.1F);
        /*    */   }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (amount * 8 - 1) + DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0) {
            flash();

            addResolutionCard(card, action);

            for (int i = 0; i < amount * 8; i++)
            {
                PlayCopyOfCard(card, action);
            }

            this.amount--;
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, OctetPower.POWER_ID));
            }

        }
    }

    private static void addResolutionCard(AbstractCard card, UseCardAction action) {
        AbstractCard tmp = new OctetResolution();
        AbstractMonster m = null;

        if (action.target != null) {
            m = (AbstractMonster) action.target;
        }
        tmp.purgeOnUse = true;
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), false);
    }

    private static void PlayCopyOfCard(AbstractCard card, UseCardAction action) {
        AbstractMonster m = null;

        if (action.target != null) {
            m = (AbstractMonster) action.target;
        }

        AbstractCard tmp = card.makeSameInstanceOf();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F;

        if (m != null) {
            tmp.calculateCardDamage(m);
        }

        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
    }
}