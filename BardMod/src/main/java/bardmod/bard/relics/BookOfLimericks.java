package bardmod.bard.relics;

import bardmod.BardMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BookOfLimericks extends CustomRelic {
    public static final String ID = "BookOfLimericks";
    public BookOfLimericks() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BookOfLimericks();
    }

    public void beforeEnergyPrep() {
        boolean isEliteOrBoss = (AbstractDungeon.getCurrRoom()).eliteTrigger;
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
                break;
            }
        }

        if (!isEliteOrBoss) {
            beginLongPulse();
            flash();
            AbstractDungeon.player.energy.energyMaster++;
        }
    }

    public void onVictory() {
        if (this.pulse) {
            AbstractDungeon.player.energy.energyMaster--;
            stopPulse();
        }
    }
}
