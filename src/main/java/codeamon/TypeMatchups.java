package codeamon;

import java.util.List;
import java.util.Arrays;
import java.util.List;

/**
 * TypeEffectiveness is a class that knows which types are super effective and not very effective
 * against all the other types.
 */
public class TypeMatchups {
    /**
     * TODO: For now, type matchups that deal no damage are treated as not very effective. If there
     * is time to implement an "AI" that can determine effectiveness before selecting a move,
     * implement the 0 damage option
     */

    //List of types the attack is super effective against
    private static final List<Type> fireSuper = Arrays.asList(Type.Grass, Type.Ice, Type.Bug,
                                    Type.Steel);
    private static final List<Type> waterSuper = Arrays.asList(Type.Fire, Type.Ground, Type.Rock);
    private static final List<Type> grassSuper = Arrays.asList(Type.Water, Type.Ground, Type.Rock);
    private static final List<Type> electricSuper = Arrays.asList(Type.Water, Type.Flying);
    private static final List<Type> iceSuper = Arrays.asList(Type.Grass, Type.Ground, Type.Flying,
                                    Type.Dragon);
    private static final List<Type> fightingSuper = Arrays.asList(Type.Normal, Type.Ice, Type.Rock,
                                    Type.Dark, Type.Steel);
    private static final List<Type> poisonSuper = Arrays.asList(Type.Grass, Type.Fairy);
    private static final List<Type> groundSuper = Arrays.asList(Type.Fire, Type.Electric,
                                    Type.Poison, Type.Rock, Type.Steel);
    private static final List<Type> flyingSuper = Arrays.asList(Type.Grass, Type.Fighting,
                                    Type.Bug);
    private static final List<Type> psychicSuper = Arrays.asList(Type.Fire, Type.Poison);
    private static final List<Type> bugSuper = Arrays.asList(Type.Grass, Type.Psychic, Type.Dark);
    private static final List<Type> rockSuper = Arrays.asList(Type.Fire, Type.Ice, Type.Flying,
                                    Type.Bug);
    private static final List<Type> ghostSuper = Arrays.asList(Type.Psychic, Type.Ghost);
    private static final List<Type> dragonSuper = Arrays.asList(Type.Dragon);
    private static final List<Type> darkSuper = Arrays.asList(Type.Psychic, Type.Ghost);
    private static final List<Type> steelSuper = Arrays.asList(Type.Ice, Type.Rock, Type.Fairy);
    private static final List<Type> fairySuper = Arrays.asList(Type.Fighting, Type.Dark,
                                    Type.Dragon);

    //List of types the attack is not very effective against
    private static final List<Type> normalWeak = Arrays.asList(Type.Rock, Type.Ghost, Type.Steel);
    private static final List<Type> fireWeak = Arrays.asList(Type.Fire, Type.Water, Type.Rock,
                                    Type.Dragon);
    private static final List<Type> waterWeak = Arrays.asList(Type.Water, Type.Grass, Type.Dragon);
    private static final List<Type> grassWeak = Arrays.asList(Type.Fire, Type.Grass, Type.Poison,
                                    Type.Flying,
            Type.Bug, Type.Dragon, Type.Steel);
    private static final List<Type> electricWeak = Arrays.asList(Type.Grass, Type.Electric,
                                    Type.Dragon, Type.Ground);
    private static final List<Type> iceWeak = Arrays.asList(Type.Fire, Type.Water, Type.Ice,
                                    Type.Steel);
    private static final List<Type> fightingWeak = Arrays.asList(Type.Poison, Type.Flying,
                                    Type.Psychic, Type.Bug, Type.Fairy);
    private static final List<Type> poisonWeak = Arrays.asList(Type.Poison, Type.Ground, Type.Rock,
                                    Type.Ghost, Type.Steel);
    private static final List<Type> groundWeak = Arrays.asList(Type.Grass, Type.Flying, Type.Bug);
    private static final List<Type> flyingWeak = Arrays.asList(Type.Electric, Type.Rock,
                                    Type.Steel);
    private static final List<Type> psychicWeak = Arrays.asList(Type.Psychic, Type.Dark,
                                    Type.Steel);
    private static final List<Type> bugWeak = Arrays.asList(Type.Fire, Type.Fighting, Type.Poison,
                                    Type.Flying, Type.Ghost, Type.Steel, Type.Fairy);
    private static final List<Type> rockWeak = Arrays.asList(Type.Fighting, Type.Ground,
                                    Type.Steel);
    private static final List<Type> ghostWeak = Arrays.asList(Type.Dark, Type.Normal);
    private static final List<Type> dragonWeak = Arrays.asList(Type.Steel, Type.Fairy);
    private static final List<Type> darkWeak = Arrays.asList(Type.Fighting, Type.Dark, Type.Bug,
                                    Type.Fairy);
    private static final List<Type> steelWeak = Arrays.asList(Type.Fire, Type.Water, Type.Electric,
                                    Type.Steel);
    private static final List<Type> fairyWeak = Arrays.asList(Type.Fire, Type.Poison, Type.Steel);

    /**
     * Checks the attacking move's type against the target's Codeamon's type returns an appropriate
     * damage multiplier based on the effectiveness.
     *
     * @param user     The attack type
     * @param opponent The target's type
     * @return 2.0 if the attack is super-effective, 0.5 if the attack is not very effective,
     * or 1.0 otherwise
     */
    public static double getEffectiveness(Type user, Type opponent) {
        double multiplier = 1.0;

        if (user == Type.Normal) {
            if (normalWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Fire) {
            if (fireSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (fireWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Water) {
            if (waterSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (waterWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Grass) {
            if (grassSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (grassWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Electric) {
            if (electricSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (electricWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Ice) {
            if (iceSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (iceWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Fighting) {
            if (fightingSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (fightingWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Poison) {
            if (poisonSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (poisonWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Ground) {
            if (groundSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (groundWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Flying) {
            if (flyingSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (flyingWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Psychic) {
            if (psychicSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (psychicWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Bug) {
            if (bugSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (bugWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Rock) {
            if (rockSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (rockWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Ghost) {
            if (ghostSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (ghostWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Dragon) {
            if (dragonSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (dragonWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Dark) {
            if (darkSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (darkWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Steel) {
            if (steelSuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (steelWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        } else if (user == Type.Fairy) {
            if (fairySuper.contains(opponent)) {
                multiplier = 2.0;
            } else if (fairyWeak.contains(opponent)) {
                multiplier = 0.5;
            }
        }

        return multiplier;
    }

    private void message(double multiplier) {
        if (multiplier == 2.0) {
            System.out.println("It's super effective!");
        } else if (multiplier == 0.5) {
            System.out.println("It's not very effective...");
        }
    }
}