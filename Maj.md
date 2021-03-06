
# Mise à jour du système

Afin de garder la communauté des joueurs active, il est important de faire évoluer le jeu pour qu'il ne devienne pas lassant, et inciter d'autres joueurs à venir.

Pour cela, nous allons ajouter au jeu des bonus.
Tous les 15 jours, un nouveau bonus viendra enrichir le jeu (par ex: rayon du radar étendu pendant x min / tours).

## Comment faire pour éviter la coupure de service ?

Pour éviter la **coupure de service**, il faut faire en sorte de prévenir les joueurs à l’avance, pour que ces derniers ne soient pas pris au dépourvus. Ensuite, il faut faire en sorte que la maintenance soit la plus **courte possible**, pour que le service puisse repartir le plus vite possible.

## Que faire des parties en cours ?

Il ne sera **plus possible de créer de nouvelles parties une demi-heure avant la MAJ**. Un **message en jeu** apparaitra pour prévenir les joueurs qu’ils ne peuvent plus rejoindre de parties, et il s’actualisera tout les certains temps pour que les joueurs sachent combien de temps il reste avant que la maintenance ne se fasse.Passé ce délai, **les joueurs qui ne seront pas en parties seront deconnectés**. Les parties qui seraient éventuelllement en cours **pourront être terminée**. Une fois qu’il n’y aura **plus aucune partie en cours**, la mise à jour pourra être faite.

## Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétro-compatibilité)

En utilisant des **outils de compatibilité**, qui permettront aux navigateurs plus vieux d’être supportés peut importe la version du jeu. Il faut également privilégier le plus possible des modules qui sont largement compatibles, et n’utiliser des modules/packages qui peuvent créer des problèmes de compatibilités **UNIQUEMENT** s’il n’est pas possible de rajouter un élément autrement.

## Comment avertir les joueurs de la nouveauté une unique fois ?

Quand le joueur rencontre pour la première fois un nouvel élément en partie, le jeu affichera un **message de présentation de l’élément sur l’écran du joueur**, avec **ses attribut** ( le type de bonus que c’est, sur quoi il agit sa durée).
