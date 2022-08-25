package bardmod.bard.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(clz= AbstractPlayer.class, method="damage", paramtypez = DamageInfo.class)
public class NeowPowerPatch {

    @SpireInsertPatch(loc = 1853)
    public static SpireReturn Insert(AbstractPlayer __instance, DamageInfo damageInfo)
    {
        System.out.println("NeowPowerPatch hit.");
        if (__instance.hasPower(NeowPower.POWER_ID))
        {
            __instance.currentHealth = 0;
            AbstractPower neowPower = __instance.getPower(NeowPower.POWER_ID);
            neowPower.onVictory();
            return SpireReturn.Return();
        }

        return SpireReturn.Continue();
    }
}
