import { User } from './User';

export interface Post {
    postId;

    author : User[];

    text;

    image;

    media_name;

    timestamp;

    dateCreated;

    reactions : Reaction[];

    score : number;

    userReaction : any;

    userIsReacting : boolean;
}

export interface Reaction {
    reactionID : number;

    value : number;

    reactor: User;
}

export function getReactionScore(post: Post) : number
{
    let score = 0;
    post.reactions.forEach(reaction => {
        score += reaction.value;
    });

    return score;
}
