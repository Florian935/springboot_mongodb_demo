# Projet Client/Serveur MARTIN Florian / RENAUD Nathan

## Fonctionnement de l'application

Le but de ce projet est de proposer la gestion des articles d'une application.

Un article contient un ___titre___, un ___contenu___, l'___utilisateur___ ayant publié l'article, une ___date de publication___, des ___catégories___ et des ___commentaires___.

Une catégorie contient uniquement un ___label___.

Un commentaire contient un ___id___ et un ___contenu___.

Un utilisateur contient un ___pseudo___, un ___mot de passe___, la liste de tous les ___commentaires___ qu'il a écrit, la liste de tous les ___articles___ qu'il a publié et une ___adresse___.

## Règles métiers de l'application

Un utilisateur peut publier 1 ou plusieurs articles. Un utilisateur peut publier un ou plusieurs commentaires sur un article.

Un article peut avoir de 0 à plusieurs commentaires. Un article ne possède qu'un seul utilisateur ayant publié cet article. Un article peut avoir de 1 à plusieurs catégories.

## Structure du projet

Au sein de ce projet, il existe 3 différents services permettant de gérer les méthodes CRUD des 3 entités différentes à savoir:
- Les `commentaires`
- Les `articles`
- Les `utilisateurs` 

Les différents services se trouvent dans le package `service`.

Egalement, à chaque entités correspond un contrôleur présent dans le package `controller`.

Dans le package `exception` sont présentes toutes les classes permettant de gérer les exceptions personnalisées ainsi que les codes de retour HTTP. Ces exceptions correspondent aux différentes règles métiers de l'application.

## Indexation

Nous avons fait le choix dans ce projet d'ajouter des ___index___ dans les collections `Article` et `Utilisateur`.

La collection Article contient des index sur les champs ___titre___, ___utilisateur___ et ___publishedDate___. Il nous paraît utile de pouvoir effectuer des requêtes des requêtes performantes lorsqu'on souhaite chercher le titre d'un article, qu'on souhaite chercher tous les articles correspondant à un utilisateur ou encore de retrouver tous les articles par la date de publication.

Pour la collection Utilisateur, elle contient un index sur le champ ___pseudo___ ce qui nous paraît très utile pour chercher de façon optimale un utilisateur en base de données en se basant sur le pseudo.

## Documents dénormalisées et documents liées par référence

Concernant la dénormalisation, nous avons fait le choix de dénormaliser ___l'utilisateur___, la ___catégorie___ et les ___commentaires___ dans la collection Article. Dans la collection Utilisateur, nous avons effectué une dénormalisation sur le champ ___adresse___.

Concernant les documents liées par référence, nous avons fait le choix d'en effectuer sur les ___commentaires___ et les ___articles___ dans la collection Utilisateur. 

## Requête de recherche

Nous avons effectué plusieurs requêtes permettant de chercher des documents selon des critères données. Nous en avons effectué sur les collections ___Article___ et ___Utilisateur___.

Ces requêtes de recherches peuvent être retrouvées dans le package `repository` et dans les interfaces ___ArticleRepository___ et ___UserRepository___.

Par exemple, on peut retrouver dans le repository des articles une requête contenant un `$group` qui permet de compter le nombre d'articles présent en base.
On peut également retrouver une requête contenant un `$project` et un `$ifNull` et permettant de compter le nombre de commentaires par articles.

Egalement, on peut retrouver des requêtes de recherches plus classique, par exemple dans le repository des utilisateurs, permettant de rechercher à partir du pseudo des utilisateurs.

## Fonctionnement de l'API

Afin d'intéragir avec l'API, il faudra consulter les contrôleurs et les différents endpoints exposés. A chaque méthode est associées la JavaDoc dans le code.

Par soucis de facilité, voici cependant ci-dessous un tableau récapitulatif permettant de faire correspondre les endpoints exposés et leurs utilités.

## Endpoints exposés


### Articles:

| Endpoint        		  | Verbe HTTP | Description  							|
| :--------------------:  | :--------: | :------------------------------------: |
| /api/v1.0/articles      | `GET`      | Retourne la liste de tous les articles |
| /api/v1.0/articles/{id}  | `GET`      | Retourne l'article correpondant à l'id fourni en paramètre |
| /api/v1.0/articles  | `POST`      | Enregistre l'article fourni dans le corps de la requête |
| /api/v1.0/articles/{id}  | `DELETE`      | Supprime l'article correpondant à l'id fourni en paramètre |
| /api/v1.0/articles/{id}  | `PUT`      | Met à jour l'article correspondant à l'id fourni en paramètre et avec l'article fourni dans le corps de la requête |
| /api/v1.0/articles/search/titre/exact-matching?titre={value}  | `GET`      | Permet de chercher les articles qui correspondent au titre fourni en query param de la requête. A noter que le titre doit correspondre ___exactement___ (sensible à la classe, prend en compte les espaces etc). |
| /api/v1.0/articles/search/titre/containing?titre={value}  | `GET`      | Permet de chercher les articles dont le titre contient le critère de recherche passé en query param de la requête. La recherche n'est pas sensible à la casse. |
| /api/v1.0/articles/search/published-date/between?from={yyyy-MM-dd}&to={yyyy-MM-dd}  | `GET`      | Permet de chercher les articles dont la date de publication est comprise entre le paramètre "from" et le paramètre "to" (qui sont fournis en paramètre de la requête). La date doit avoir le format suivant: ___2021-06-28___ |
| /api/v1.0/articles/search?titre={value}&from={yyyy-MM-dd}&to={yyyy-MM-dd}  | `GET`      | Permet de chercher les articles dont le titre est contenu dans l'article (insensible à la casse), et dont la date de publication est comprise entre le paramètre "from" et le paramètre "to" (qui sont fournis en paramètre de la requête). La date doit avoir le format suivant: ___2021-06-28___ |
| /api/v1.0/articles/count  | `GET`      | Permet d'obtenir le nombre d'articles présents en base de données |
| /api/v1.0/articles/commentary/count  | `GET`      | Permet d'obtenir le nombre de commentaire par article |

### Utilisateurs:

| Endpoint        		  | Verbe HTTP | Description  							|
| :--------------------:  | :--------: | :------------------------------------: |
| /api/v1.0/users      | `GET`      | Retourne la liste de tous les utilisateurs |
| /api/v1.0/users/{id}  | `GET`      | Retourne l'utilisateur correpondant à l'id fourni en paramètre |
| /api/v1.0/users  | `POST`      | Enregistre l'utilisateur fourni dans le corps de la requête |
| /api/v1.0/users/{id}  | `DELETE`      | Supprime l'utilisateur correpondant à l'id fourni en paramètre |
| /api/v1.0/users/{id}  | `PUT`      | Met à jour l'utilisateur correspondant à l'id fourni en paramètre et avec l'utilisateur fourni dans le corps de la requête |
| /api/v1.0/users/search/pseudo/exact-matching?pseudo={value}  | `GET`      | Permet de chercher les utilisateurs qui correspondent au pseudo fourni en query param de la requête. A noter que le pseudo doit correspondre ___exactement___ (sensible à la classe, prend en compte les espaces etc). |
| /api/v1.0/users/search/pseudo/containing?pseudo={value}  | `GET`      | Permet de chercher les utilisateurs dont le pseudo contient le critère de recherche passé en query param de la requête. La recherche n'est pas sensible à la casse. |

### Commentaires:

| Endpoint        		  | Verbe HTTP | Description  							|
| :--------------------:  | :--------: | :------------------------------------: |
| /api/v1.0/commentaires      | `GET`      | Retourne la liste de tous les commentaires |
| /api/v1.0/commentaires/{id}  | `GET`      | Retourne le commentaire correpondant à l'id fourni en paramètre |
| /api/v1.0/commentaires  | `POST`      | Enregistre le commentaire fourni dans le corps de la requête |
| /api/v1.0/commentaires/{id}  | `DELETE`      | Supprime le commentaire correpondant à l'id fourni en paramètre |
| /api/v1.0/commentaires/{id}  | `PUT`      | Met à jour le commentaire correspondant à l'id fourni en paramètre et avec le commentaire fourni dans le corps de la requête |
