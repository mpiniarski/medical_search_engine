export interface SearchBody {
    query: string;
    sortingAlgorithm: string;
}

export interface SearchBodyWithWeights {
    query: string;
    sortingAlgorithm: string;
    weights: Object;
    positiveArticles: Object[];
    negativeArticles: Object[];
}
